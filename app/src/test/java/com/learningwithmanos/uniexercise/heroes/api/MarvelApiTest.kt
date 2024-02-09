package com.learningwithmanos.uniexercise.heroes.api


import com.learningwithmanos.uniexercise.heroes.api.request.MarvelApi
import com.learningwithmanos.uniexercise.heroes.api.response.Comics
import com.learningwithmanos.uniexercise.heroes.api.response.MarvelResultCharacters
import com.learningwithmanos.uniexercise.heroes.api.response.Result
import com.learningwithmanos.uniexercise.heroes.api.response.Thumbnail
import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.source.remote.DummyRestFrameworkWrapper
import com.learningwithmanos.uniexercise.heroes.utils.constants.Constants
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response
import okhttp3.MediaType.Companion.toMediaType


