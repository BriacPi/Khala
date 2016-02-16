
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/corpus/Desktop/khala/conf/routes
// @DATE:Tue Feb 16 11:54:49 CET 2016

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseContentController ContentController = new controllers.ReverseContentController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseMyAssets MyAssets = new controllers.ReverseMyAssets(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseAuth Auth = new controllers.ReverseAuth(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseUserProfileController UserProfileController = new controllers.ReverseUserProfileController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseApplication Application = new controllers.ReverseApplication(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseContentController ContentController = new controllers.javascript.ReverseContentController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseMyAssets MyAssets = new controllers.javascript.ReverseMyAssets(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseAuth Auth = new controllers.javascript.ReverseAuth(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseUserProfileController UserProfileController = new controllers.javascript.ReverseUserProfileController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseApplication Application = new controllers.javascript.ReverseApplication(RoutesPrefix.byNamePrefix());
  }

}
