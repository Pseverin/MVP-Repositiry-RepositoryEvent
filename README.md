# View-Presenter-Repository + RepositoryEvent architechture
Here you can find an example app, that implements improved variant of MVP architechture.

![diagram_1](/resources/diagram_1.png?raw=true)

RepositoryEvent
---------------
RepositoryEvent is a special wrapper class, that helps to provide propper UI lifecyle while app is working with data.


Working remoted or large data
---------------
1. Presenter calls Interactor to return Observable with data
2. Interactor desides, that data must be called from Remote Repository, e.g. ApiRepository
3. Remote Repository form Observable and emit empty RepositoryEvent with flag START_LONG_ACTION
4. Presenter->View responds on this RepositoryEvent
5. Remote Repository emit RepositoryEvent with data
6. Presenter->View responds on this event and receives RepositoryEvent with data

Working local "fast" data
---------------
1. Presenter calls Interactor to return Observable with data
2. Interactor desides, that data must be called from Local Repository, e.g. MemoryRepository
3. Local Repository emit RepositoryEvent with data
4. Presenterreceives RepositoryEvent with data

RepositoryEvent tools
---------------
RepositoryEvent has wide list of transforming tools like *filter*, *map*, *flatmap*, *toObservable* etc.

License
-------
Copyright (c) 2017 TheAppSolutions. All rights reserved.

