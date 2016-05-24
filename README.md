### Clojure [Boot](https://github.com/boot-clj/boot) - [Maven](https://github.com/apache/maven) interop

I like the IDEA editor and Cursive plugin for editing Clojure. Unfortunately there is no yet Boot integration in Cursive. The biggest thing I have been missing there was dependencies' integration: each time I had to enter dependencies manually in *build.boot* config file and in *pom.xml* and this welcomed optimization.

Now, why do I use Maven build system in IDEA for editing Clojure code? Because IDEA has the best built-in support for Maven. However ugly XML config file might look these days, working with *pom.xml* is actually very convenient. IDEA indexes remote repositories and gives you hints about packages'  names and even their versions. Moreover, with Maven build system, IDEA stores dependencies in standard local Maven repository, the same one which is used by Boot, so in this set up you do not have to retrieve same files twice. If you are using IDEA only as an editor, as I do, and Boot for everything else, then as soon as you can make Boot work with Maven dependencies, the biggest part of Boot-Cursive integration would be done. 

This code does exactly that -- it lets you have the one "source of truth" for dependencies' declaraition in Boot - Cursive interop. You declare dependecies only in *pom.xml* file and Boot will import them automatically on each run. Here we can see a strong ieature of Boot (over Leinigen) in action: having a config file as a code (instead of a data), is a big win for this task.


#### How to use

Import (c&p) *build.boot* file into your boot config. Then use 
~~~~
(set-env! :dependencies (maven-import))
~~~~
instead of usual data dependecies declaration. *pom.xml* should be in the same directory.

As I am yet studying Clojure  your comments are welcome, but this code basically works for me. The formatting is made compact on purpose.
