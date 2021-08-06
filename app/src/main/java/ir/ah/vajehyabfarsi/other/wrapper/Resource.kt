package ir.ah.vajehyabfarsi.other.wrapper

sealed class Resource<out T>(
    open val success: T? = null,
    open val failure: FailureInterface? = null
) {
    object Loading : Resource<Nothing>()

    class Success<T>(override val success: T) : Resource<T>(success = success)

    class Failure<T>(override val failure: FailureInterface) : Resource<T>(failure = failure)
}

