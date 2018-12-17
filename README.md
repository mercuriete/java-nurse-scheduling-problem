# Phase 2 - Web Developer code exercise

[![Build Status](https://travis-ci.org/mercuriete/java-nurse-scheduling-problem.svg?branch=master)](https://travis-ci.org/mercuriete/java-nurse-scheduling-problem)

## Introduction

Welcome to this coding exercise. We hope you enjoy doing this
exercise as much as we did while preparing it. Please read carefully
this document and don’t hesitate in asking any questions you might
have.

The problem has been designed so the solution can be done
incrementally. We recommend you work on the deliverables in the
order they have been defined, so you can start with a basic solution
and then go for the extra requirements.

Good luck!

## Problem context

The temporary recruitment agency called _TeneJob_ wants to optimize
the way they assign their workers to the different shifts of the jobs
they work with.

In order to help them with this mission, you need to build an API
endpoint that will accept a list of workers and shifts and return the
best pairing options.

## Problem domain

The problem has 3 main entities: Worker, Shifts, and Matchings.

A **Worker** has a weekly availability which is the days of the week
(not including weekends) they can work. For example, a worker
might only be able to work on Mondays and Wednesdays. Also, a
worker might have a payrate, which is the amount of money paid to
him for each shift he works on.

A **Shift** is a piece of work that needs to be done by a worker. To
simplify the problem, you can consider a shift takes a whole day to be
completed.

A **Matching** is the pairing of one worker with one shift. Two rules
apply to shifts:

- A worker can only work on one shift during the same day
- A shift can only be matched with one worker


## Problem deliverables

1) REQUIRED: Provide an API endpoint that receives a list of workers
and a list of shifts and returns the optimal list of matchings. **A list of
matchings is optimal if each worker is paired with at least one
shift**. Keep in mind that in some scenarios, your algorithm won't able
to get an optimal solution. In that case, you should be able to detect
the situation and return a message indicating this.

## Implementation requirements

You will need to provide a _dockerized_ system we can run as a docker
container, to test your solution. **This requirement is mandatory** ,
we won’t be evaluating any solution that cannot be tested using
docker container.

Optionally, you can try to deploy your _dockerized_ application in some
free service like Heroku, just to make easier the evaluation from our
side.

You’re free to build this API in any language you consider
appropriate, using any web server, framework or library you want to
add.

The endpoint input and output should be sent in JSON format and it
need to be REST compliant. The structure and an example of these
are provided as an example in a separate file alongside this
document.

Please provide any documentation you consider necessary for us to
test your endpoint (e.g. port number, known limitations).


## Evaluation

We will be focusing on reviewing the following aspects of your
solution:

- How organized your code is, the structure of your application,
    and design patterns
- The efficiency of your method to generate the matchings
- Documentation and code readability
- Good development practices:
    o Unit tests
    o Input data validation
    o Error handling
    o ...

Please submit your solution as a zipped git repository or upload it to
any cloud code repository (preferable Github or Gitlab).

