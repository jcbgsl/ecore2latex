ecore2latex
===========

The idea of this project is to provide an Eclipse plugin which allows to generate a documentation in LaTeX from an ecore model. The layout for this documentation is inspired by the UML specifications.

The feature will be installable as a plugin in an Eclipse and will enable the creation of a .tex file for an ecore model. 
The transformation will take into account the structure of the model (EPackage, EClass, EEnumeration, ...) as well as annotations added to the model. 
Ideally, some configuration options will be possible, as the NS and key values for the annotations, the output file etc.

The M2T transformation will done by Acceleo based templates, as well as some Java code.

The project will be built using maven and tycho, two technologies I've been appreciating a lot recently.

The CI is done on buildhive, and the actual status is ![Build Status](https://buildhive.cloudbees.com/job/jcbgsl/job/ecore2latex/badge/icon).

An update site with the current release, as well as as an update site with milestones will be coming soon... 

