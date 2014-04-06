alpaca-tracker
==============

An application created as a class project for COS420 (Software Engineering) at the University of Maine. Intended to serve as both a frontend and backend application used to manage and track alpaca herds.

##Notes

#####General

#####Jon

I've created the first commit through Eclipse using EGit. The basic workflow after configuration is:
* Pull from repository
* Do work
* Commit and push changes

I think I want the program to be structured in a way that's similar to Unity. That is, there are
Update, Init, and Exit methods, and a class that controls program flow that implements these.

#####Clay

#####Sylvia

#####Jake

##Firebase

This application utilizes [Firebase](https://www.firebase.com/) to maintain data between the frontend and backend, as well as update in real-time. The frontend utilizes the [AngularFire](https://github.com/firebase/angularFire) library. The following guides describe the use of Firebase:

- [Using the Java SDK](https://www.firebase.com/docs/java-quickstart.html)
- [Using AngularFire](https://www.firebase.com/quickstart/angularjs.html)

##Code Style

Pascal and Camel Casing are used for consistency in this project. More details can be found [here](http://msdn.microsoft.com/en-us/library/x2dbyw72(v=vs.71).aspx),  but the basics are that variables are lower case and method/interface names are upper case for the first letter:

```java
public int variable;
public int otherVariable;

/**
 * Javadoc comment
 */
public void MethodName(){
	// Comment
}
```

and so on.



This document uses Markdown for styling. Find more info [here](http://help.github.com/articles/markdown-basics)
and [here](http://help.github.com/articles/github-flavored-markdown).
