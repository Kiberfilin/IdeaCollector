package com.example.core_impl.clean.domain

import com.example.core_api.clean.domain.boundaries.use_cases.DeleteIdeaInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.GetAllIdeasInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.InsertIdeaInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.UpdateIdeaInputPort
import com.example.core_impl.clean.domain.usecases.DeleteIdeaInteractor
import com.example.core_impl.clean.domain.usecases.GetAllIdeasInteractor
import com.example.core_impl.clean.domain.usecases.InsertIdeaInteractor
import com.example.core_impl.clean.domain.usecases.UpdateIdeaInteractor
import dagger.Binds
import dagger.Module

@Module
interface UseCasesModule {
    @Binds
    fun bindGetAllIdeasInputPort(interactor: GetAllIdeasInteractor): GetAllIdeasInputPort

    @Binds
    fun bindInsertIdeaInputPort(interactor: InsertIdeaInteractor): InsertIdeaInputPort

    @Binds
    fun bindUpdateIdeaInputPort(interactor: UpdateIdeaInteractor): UpdateIdeaInputPort

    @Binds
    fun bindDeleteIdeaInputPort(interactor: DeleteIdeaInteractor): DeleteIdeaInputPort
}