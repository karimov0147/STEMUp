# STEMUp
Learn STEM with fun

    # app -> package for tracking application lifecycle

    # the data package includes 3 packages, they are : models , locale , storage 
        local -> here is the orm (local database) classes such as DAO, the successor of the room class and entity classes
        models -> here is the models that are used inside the application to exchange information
        storage -> here is a local class as shared preference
    
    # domain -> here is the repositories class, it is responsible for all the information in the application

    # UI package contains packages like adapters , presenters and screens
        adapters -> adapters contains all adapters that use recycler view
        presentosr -> presentors contains view models that manipulate data from repositories and deliver it to screens
        screens -> the screen contains all fragments or each window that is visible in the application.
    
    # utils -> here are different extension functions and different static arrays and matrices

Ps. The code is written very quickly, so the coding style is not in the clean architecture and there is little optimization
