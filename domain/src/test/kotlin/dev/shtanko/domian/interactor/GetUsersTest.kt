package dev.shtanko.domian.interactor

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import dev.shtanko.common.Either
import dev.shtanko.domain.gateway.UsersGateway
import dev.shtanko.domain.interactor.GetUsers
import dev.shtanko.domian.UnitTest
import org.junit.Before
import org.junit.Test

class GetUsersTest : UnitTest() {
    private lateinit var getUsers: GetUsers

    private val usersGateway = mock<UsersGateway> {
        on {
            runBlocking {
                getUsers(0, 0, 0)
            }

        } doReturn (Either.Right(listOf()))
    }

    @Before
    fun setUp() {
        getUsers = GetUsers(usersGateway)
    }

    @Test
    fun `should get data from repository`() {

        runBlocking { getUsers.run(GetUsers.Params(0, 0, 0)) }

        runBlocking {
            verify(usersGateway).getUsers(0, 0, 0)
        }
    }
}