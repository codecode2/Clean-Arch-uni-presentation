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
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response
import okhttp3.MediaType.Companion.toMediaType


class DummyRestFrameworkWrapperTest {

    @Test
    fun testGetHeroes_SuccessfulResponse() = runBlocking {
        // Mock your dependencies
        val mockApi = mock(MarvelApi::class.java)
        val dummyRestFrameworkWrapper = DummyRestFrameworkWrapper(mockApi)

        // Mock the API response
        val mockMarvelCharacter = Result(
            id = 1,
            name = "Spider-Man",
            resourceURI = "mockResourceURI",
            comics = Comics(10), // Sample data for available comics
            thumbnail = Thumbnail(path = "mockPath", extension = "jpg")
        )



        // Call the function you want to test
        val result: List<Hero> = dummyRestFrameworkWrapper.getHeroes()

        // Assert the result
        assertEquals(1, result.size)
        assertEquals("Spider-Man", result[0].name)
        assertEquals(10, result[0].availableComics)
        // Add more assertions based on your specific data mapping logic
    }

    @Test
    fun testGetHeroes_FailureResponse() = runBlocking {
        // Mock your dependencies
        val mockApi = mock(MarvelApi::class.java)
        val dummyRestFrameworkWrapper = DummyRestFrameworkWrapper(mockApi)

        // Mock the API response with an error
        val mockResponse = Response.error<MarvelResultCharacters>(404, mockResponseBody())
        `when`(mockApi.getAllCharacters(Constants.PUBLIC_KEY, Constants.timeStamp, Constants.hash(), Constants.limit, 0))
            .thenReturn(mockResponse)

        // Call the function you want to test and expect an exception
        try {
            dummyRestFrameworkWrapper.getHeroes()
            // Fail if no exception is thrown
            throw AssertionError("Expected exception not thrown")
        } catch (e: Exception) {
            // Assert the exception message or handle it as needed
            assertEquals("Error fetching heroes: 404 - HTTP error", e.message)
        }
    }

    private fun mockResponseBody(): okhttp3.ResponseBody {
        return okhttp3.ResponseBody.create("application/json".toMediaType(), "HTTP error")
    }
}


