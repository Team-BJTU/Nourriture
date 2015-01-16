// Module Dependencies and Setup

var express = require('express')
  , mongoose = require('mongoose')
  , UserModel = require('./models/user')
  , User = mongoose.model('User')
  , welcome = require('./controllers/welcome')
  , users = require('./controllers/users')
  , http = require('http')
  , path = require('path')
  , engine = require('ejs-locals')
  , flash = require('connect-flash')
  , passport = require('passport')
  , LocalStrategy = require('passport-local').Strategy
  , expressValidator = require('express-validator')
  , mailer = require('express-mailer')
  , config = require('./config')
  , bcrypt = require('bcrypt')
  , app = express();

var get = require('./routes/get.js');
var db = require('./lib/db.js').db;
var crud = require('./routes/crud.js');

GLOBAL.db = new db();

app.configure(function(){
    app.use(express.bodyParser());    
    app.engine('ejs', engine);
    app.set('port', process.env.PORT || 3000);
    app.set('views', __dirname + '/views');
    app.set('view engine', 'ejs');
    app.use(express.favicon());
    app.use(express.logger('dev'));
    app.use(expressValidator);
    app.use(express.methodOverride());
    app.use(express.cookieParser('your secret here'));
    app.use(express.session());
    app.use(flash());
    app.use(passport.initialize());
    app.use(passport.session());
});


app.use(function(req, res, next){
  res.locals.userIsAuthenticated = req.isAuthenticated(); 
  res.locals.user = req.user; 
  res.locals.errorMessages = req.flash('error'); 
  res.locals.successMessages = req.flash('success'); 
  app.locals.layoutPath = "../shared/layout";
  next();
});

// Mailer Setup

mailer.extend(app, {
  from: 'no-reply@example.com',
  host: 'smtp.mandrillapp.com', // hostname
  port: 587, // port for Mandrill
  transportMethod: 'SMTP', // default is SMTP. Accepts anything that nodemailer accepts
  auth: {
    user: config[app.get('env')].MANDRILL_USERNAME,
    pass: config[app.get('env')].MANDRILL_API_KEY
  }
});


app.use(express.static(path.join(__dirname, 'public')));
app.use(app.router);

if ('development' == app.get('env')) {
  app.use(express.errorHandler());
} else {
  app.use(function(err, req, res, next) {
    res.render('errors/500', { status: 500 });
  });
}

// Database Connection

if ('development' == app.get('env')) {
  mongoose.connect('mongodb://localhost/nodedemo');
} else {
  // insert db connection for production
}


passport.serializeUser(function(user, done) {
  done(null, user.id);
});

passport.deserializeUser(function(id, done) {
  User.findById(id, function(err, user) {
    done(err, user);
  });
});


//login
passport.use(new LocalStrategy(
  function(username, password, done) {
    User.findOne({ username: username }, function (err, user) {
      if (err) return done(err);
      if (!user) return done(null, false, { message: "Sorry, we don't recognize that username." });
      user.validPassword(password, function(err, isMatch){
        if(err) return done(err);
        if(isMatch) return done(null, user);
        else done(null, false, { message: 'Incorrect password.' });
      });
    });
  }
));

function ensureAuthenticated(req, res, next){
  if (req.isAuthenticated()) return next();
  req.flash('error', 'Please sign in to continue.');
  var postAuthDestination = req.url;
  res.redirect('/login?postAuthDestination='+postAuthDestination);
}

function redirectAuthenticated(req, res, next){
  if (req.isAuthenticated()) return res.redirect('/');
  next();
}


var loginFunction = function(req, res, done) { 
    // check dans la bdd si le user exit ou non
    User.findOne({ 'username' :  req.body.username }, 
		 function(err, user) {
		     if (err)
			 console.log("error : " + err);
		     if (!user){
			 console.log('User Not Found with username '+username);
		     }
		     // si le user exist mais mauvais mot de passe 
		     if (!isValidPassword(user, req.body.password)){
			 console.log('Invalid Password');
		     }
		     console.log("login success");
		     res.redirect('/dashboard');
		 }
		);
}


function isValidPswd(user, password){
 return compareSync(password, user.password);    
}


//sing up

var singUpUser = function(req, res, done)
{
    findOrCreateUser = function(){
      // cherche un username dans la bdd
      User.findOne({'username': req.body.username},function(err, user) {
        // en cas d'erreur return
        if (err){
          console.log('Error in SignUp: '+err);
          //return done(err);
        }
       // verif si l'utilisateur existe deja 
        if (user) {
            console.log('User already exists');
	    res.redirect('/signup');
            //return done(null, false, 
			//req.flash('message','User Already Exists'));
        } else {
          //  creation de l'user
          var newUser = new User();
          // enregistrement dans la bdd
          newUser.username = req.body.username;
          newUser.password = createHash(req.body.password);
          newUser.email = req.body.email;
          newUser.firstName = req.body.firstname;
          newUser.lastName = req.body.lastname;
	  console.log("recap" + newUser.username + " " + newUser.password);
          // save le user
          newUser.save(function(err) {
              if (err){
		  console.log('Error in Saving user: '+err);  
		  throw err;  
              }
              console.log('User Registration succesful');
	      res.write('Register succes');
              //return done(null, newUser);
          });
        }
      });
    };
    process.nextTick(findOrCreateUser);
}
/*
passport.use('signUp', new LocalStrategy(
    function (res, data, done){	
	var singUpUser = function()
	{
	    //console.log(req);
	    console.log(JSON.stringify(res));
	    var arrayData = data.split(";");
	    User.findOne({'username':arrayData[0]},function(err, user) {
		// en cas d'erreur
		if (err){
		    console.log('Error in SignUp: '+err);
		    return done(err);
		}
		// verif si l'utilisateur existe deja
		if (user) {
		    console.log('User already exists');
		    return done(null, false, 
				req.flash('message','User Already Exists'));
		} else {
		    // creation de l'utilisateur
		    var newUser = new User();
		    newUser.username = arrayData[0];
		    //newUser.password = createHash(arrayData[4]);
		    newUser.email = arrayData[3];
		    newUser.firstName = arrayData[1];
		    newUser.lastName = arrayData[2];
		    
		    // sauvegarde de l'utilisateur
		    newUser.save(function(err) {
			if (err){
			    console.log('Error in Saving user: '+err);  
			    throw err;  
			}
			console.log('User Registration succesful');    
			return done(null, newUser);
		    });
		}
	    });
	};
	process.nextTick(singUpUser);
    }
));*/

var createHash = function(password){
var salt = bcrypt.genSaltSync(10);
// Hash the password with the salt
var hash = bcrypt.hashSync(password, salt);
    return hash;
}

app.get('/', welcome.index);
app.get('/login', redirectAuthenticated, users.login);
app.get('/reset_password', redirectAuthenticated, users.reset_password);
app.post('/reset_password', redirectAuthenticated, users.generate_password_reset);
app.get('/password_reset', redirectAuthenticated, users.password_reset);
app.post('/password_reset', redirectAuthenticated, users.process_password_reset);
app.post('/login', redirectAuthenticated, users.authenticate);
//app.get('/register', redirectAuthenticated, users.register);
//app.post('/register', redirectAuthenticated, users.userValidations, users.create);
app.get('/account', ensureAuthenticated, users.account);
app.post('/account', ensureAuthenticated, users.userValidations, users.update);
app.get('/dashboard', ensureAuthenticated, users.dashboard);
app.get('/logout', users.logout);
app.get('/users', ensureAuthenticated, users.list); 
app.all('/crud/:collection/*', crud.crud);
app.get('/dashboard/:collection/?', get.get);

/* GET Registration Page */
app.get('/signup', redirectAuthenticated, users.register);
/* Handle Registration POST */
app.post('/signup', singUpUser);

var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function callback () {
  http.createServer(app).listen(app.get('port'), function(){
      console.log('Express server listening on port ' + app.get('port'));
  });
});
