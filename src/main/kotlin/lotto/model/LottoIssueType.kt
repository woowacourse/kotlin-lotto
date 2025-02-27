package lotto.model

enum class LottoIssueType(
    val issueType: String,
) {
    AUTO("자동"),
    MANUAL("수동"),
    WINNING("당첨"),
}