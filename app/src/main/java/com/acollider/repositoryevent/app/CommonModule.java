package com.acollider.repositoryevent.app;

import com.acollider.repositoryevent.managers.DialogManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Yevhen Nechyporenko <nechiporenko.evgeniy@gmail.com>
 * @copyright (c) Grossum. (http://www.grossum.com)
 * @project Nioxin
 */
@Module
@Singleton
public class CommonModule {

    @Provides
    @Singleton
    DialogManager provideDialogManager() {
        return new DialogManager();
    }
}
