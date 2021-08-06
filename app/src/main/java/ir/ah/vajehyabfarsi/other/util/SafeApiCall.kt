package ir.ah.vajehyabfarsi.other.util



import ir.ah.vajehyabfarsi.other.wrapper.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response


suspend inline fun <T : Any> safeApiCall(
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    crossinline api: suspend () -> Response<T>
): Resource<T> {
    return try {
        val response = api.invoke()
        if (response.isSuccessful) {
            Resource.Success(response.body()!!)
        } else {
            when (response.code()) {
                401 ->
                    Resource.Failure(
                        ApiCallFailure.Unauthorized(
                            response.errorBody()?.stringSuspending(ioDispatcher)
                        )
                    )
                else ->
                    Resource.Failure(
                        ApiCallFailure.OtherError(
                            response.errorBody()?.stringSuspending(ioDispatcher)
                        )
                    )
            }
        }
    } catch (noInternetException: NoInternetException) {
        Resource.Failure(ApiCallFailure.NoInternet)
    } catch (exception: Exception) {
        exception.print()
        Resource.Failure(ApiCallFailure.OtherError(exception.message, exception))
    }
}

suspend fun ResponseBody.stringSuspending(ioDispatcher: CoroutineDispatcher = Dispatchers.IO): String? =
    withContext(ioDispatcher) {
        runCatching { string() }.onFailure { it.print() }.getOrNull()
    }