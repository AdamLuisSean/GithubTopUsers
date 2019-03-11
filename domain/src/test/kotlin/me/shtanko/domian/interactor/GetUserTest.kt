package me.shtanko.domian.interactor

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import me.shtanko.common.Either
import me.shtanko.domain.entity.FullUser
import me.shtanko.domain.gateway.UsersGateway
import me.shtanko.domain.interactor.GetUser
import me.shtanko.domian.UnitTest
import org.junit.Before
import org.junit.Test

class GetUserTest : UnitTest() {
    private lateinit var getUser: GetUser

    private val testUserName = "octocat"

    private val usersGateway = mock<UsersGateway> {
        on {
            runBlocking {
                getUser(testUserName)
            }

        } doReturn (Either.Right(FullUser(0, testUserName, "", "", 0)))
    }

    @Before
    fun setUp() {
        getUser = GetUser(usersGateway)
    }

    @Test
    fun `should get data from repository`() {

        runBlocking { getUser.run(GetUser.Params(testUserName)) }

        runBlocking {
            verify(usersGateway).getUser(testUserName)
        }
    }
}