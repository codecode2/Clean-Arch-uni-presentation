package com.learningwithmanos.uniexercise.heroes.usecase


import Resource
import com.learningwithmanos.uniexercise.heroes.repo.HeroRepository

import javax.inject.Inject

interface ErrorHandling  {

}

 class ErrorHandlingImpl @Inject constructor(
       var heroRepository: HeroRepository

): ErrorHandling {


    suspend fun execute(): Resource<Unit> {
      return heroRepository.errorHandlingHeros()

  }
}