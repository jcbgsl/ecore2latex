ecore2latex
===========

The idea of this project is to provide an Eclipse plugin which allows to create a documentation in LaTeX from an ecore model. The layout of documentation is inspired by the UML specification.

The feature will be installable in an Eclipse and will enable the creation of a .tex file for ecore models. IThe transformation will take into account the structure of the model  (EPackage, EClass, EEnumeration, ...) as well as annotations added to the model. Ideally, some configuration options will be possible, as the NS and key values for the annotations, the output file etc.

The M2T transformation will done by Acceleo based templates, as well as some Java code.

The CI is done on buildhive, and the actual status is [![Build Status](https://buildhive.cloudbees.com/job/jcbgsl/job/ecore2latex/badge/icon)](https://buildhive.cloudbees.com/job/jcbgsl/job/ecore2latex/) .

