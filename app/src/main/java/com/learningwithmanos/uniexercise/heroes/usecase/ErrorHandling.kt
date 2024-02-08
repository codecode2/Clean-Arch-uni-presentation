package com.learningwithmanos.uniexercise.heroes.usecase


import com.learningwithmanos.uniexercise.heroes.repo.HeroRepository
import javax.inject.Inject

interface ErrorHandling  {

}

 class ErrorHandlingImpl @Inject constructor(
       var heroRepository: HeroRepository

): ErrorHandling {






}