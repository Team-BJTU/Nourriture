exports.index = function(req, res){
  if(req.isAuthenticated()) return res.redirect('/dashboard');
  res.render('welcome/index');
}

exports.not_found = function(req, res){
  req.flash('error', "That doesn't seem to be a page.");
  res.redirect('/');
}