# YachtCobolToJava

This is an experiment in porting COBOL code to java.

In particular, it used [opensource COBOL 4J](https://github.com/opensourcecobol/opensourcecobol4j) to convert a test project from COBOL to Java, and then the resultant machine-generated code was refactored to produce clean Java. The majority of the commits to this repo are the refactorings

The COBOL project that was used was an attempt at the Yacht puzzle on [Exercism](https://exercism.org/), this is probably the most complex puzzle in COBOL on Exercism, and so would be a decent test of the conversion process. The COBOL was written by myself, and passed all the tests on Exercism, but the initial file YACHT.cobol did not pass through COBOL 4J and produced errors YACHT.errors. This was down to my lack of experience with writing COBOL and the Exercism interpreter not being as strict as COBOL 4J, and hence some of the early commits addressed these errors to produce YACHT_FIXED.cobol, which did pass cleanly through COBOL 4J and produced the rather complex [YACHT.java](https://github.com/ihaworth/YachtCobolToJava/blob/084d1651b6bdcba100bb500747de97a639c39e2e/src/YACHT.java).

Though the code generated was complex, manually transforming the tests from Exercism showed they all passed immediately, which was a good sign that the conversion process was solid. Should the code have needed to be shipped, it could have been as it was known to be working, and so it seems that COBOL 4J could speed up the conversion process quite a lot (at least in this case). However, if bugs had been found at this stage, then it is clear that debugging this code would have been very difficult. Having said that, if problems of this nature were encountered, it would have been very early on in the process and so a decision could have been made to continue or abandon the use of the conversion tool.

Clearly, the code in it's immediately translated form was not maintainable. COBOL 4J converts the original COBOL code to a something that looks like a implementation on-top of framework that is implemented in Java, rather than as a direct translation into Java. This is understandable as the difference between the 2 languages is non-trivial and having a more direct translation would be much more difficult to automate. For this reason, the remainder of the commits are refactorings which try and push the COBOL framework to the edges of the system, to leave a Java core of logic surrounded by COBOL conversion. Note that the tests were kept green throughout this process, and so, in theory the code could be deployed at any point should the need arise.

The refactoring process started by looking at the WS_CATEGORY variable used by the EVALUATE statement in the original COBOL, as it was known to be used with constant strings and so was considered a simple place to start. As the refactoring progressed and knowledge was gained of how the code was working, more and more complex parts of the system could be addressed. Some of the elements introduce by the framework that needed to be addressed were:

1. **Data Representation.** A system is used that is close to the COBOL concept of having data and then taking different views of it. This needed replacing with Java versions of data, pushing the conversion back to the edges of the system.
2. **Execution with COBOL modules.** The code in these was extracted to Java methods, and then eventually called directly to allow migration away from this. The final code has no trace of this model in it.
3. **Computation.** Often, it was easier to re-write the computation in Java using the original COBOL code as a guide.

The resultant, refactored java code is trying to be the closest literal translation of the COBOL code possible, and should be pretty easy to compare to the original YACHT_FIXED.cobol file. It could be refactored further (for example to split out classes for each category) but that's a relatively trivial process now the code is in a reasonable place.

All in all, this experiment shows that COBOL 4J seems to have promise in helping convert legacy COBOL systems to Java, but requires reasonable refactoring skills to tidy up the generated code. Of course, further evidence should be sought of using this tool on a larger, more complex codebase to know if this approach really is useful.
