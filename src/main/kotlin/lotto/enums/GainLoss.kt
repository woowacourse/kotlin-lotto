package lotto.enums

enum class GainLoss(
    private val text: String,
    private val postposition: String,
) {
    GAIN("이득", "이라는"),
    PRINCIPAL("본전", "이라는"),
    LOSS("손해", "라는"),
    ;

    val fullText: String
        get() = text + postposition
}
