package model

enum class ProfitStatus(
    val status: String,
) {
    GAIN("이득"),
    LOSS("손해"),
    EVEN("본전"),
}
