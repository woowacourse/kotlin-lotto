package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoResultTest {
    @ParameterizedTest
    @CsvSource(
        "FIRST, 0",
        "SECOND, 2",
        "THIRD, 1",
        "FOURTH, 4",
        "FIFTH, 2",
    )
    fun `Map에서 Rank에 대한 당첨 개수를 반환한다`(
        rank: Rank,
        expected: Int,
    ) {
        val rankMap: MutableMap<Rank, Int> = mutableMapOf()
        rankMap[rank] = expected
        val lottoResult = LottoResult(rankMap)
        val actual = lottoResult.setNullZero(rank)
        assertThat(actual).isEqualTo(expected)
    }
}
