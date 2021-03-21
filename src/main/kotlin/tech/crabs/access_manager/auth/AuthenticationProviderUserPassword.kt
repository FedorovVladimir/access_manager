package tech.crabs.access_manager.auth

import io.micronaut.context.annotation.Value
import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.*
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import org.reactivestreams.Publisher
import javax.inject.Singleton


@Singleton
class AuthenticationProviderUserPassword : AuthenticationProvider {

    @Value("\${auth.login}")
    private lateinit var login: String

    @Value("\${auth.password}")
    private lateinit var password: String

    override fun authenticate(
        httpRequest: HttpRequest<*>?,
        authenticationRequest: AuthenticationRequest<*, *>?
    ): Publisher<AuthenticationResponse> {
        return Flowable.create({
            if (authenticationRequest != null && authenticationRequest.identity == login && authenticationRequest.secret == password) {
                it.onNext(UserDetails(authenticationRequest.identity.toString(), emptyList()))
                it.onComplete()
            } else {
                it.onError(AuthenticationException(AuthenticationFailed()))
            }
        }, BackpressureStrategy.ERROR)
    }
}
