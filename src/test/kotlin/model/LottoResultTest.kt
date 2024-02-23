package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoResultTest {
    @Test
    fun `Map의 내부의 value에 null이 있을 경우 0을 반환한다`() {
        val rankMap = emptyMap<Rank, Int>()
        val lottoResult = LottoResult(rankMap)
        val actual = lottoResult.getNum(Rank.THIRD)
        assertThat(actual).isEqualTo(0)
    }

    @ParameterizedTest
    @CsvSource(
        "FIRST, 1",
        "SECOND, 2",
        "THIRD, 3",
        "FOURTH, 4",
        "FIFTH, 5",
    )
    fun `Map의 내부의 value가 null이 아닐 경우 제대로 반환한다`(
        rank: Rank,
        expected: Int,
    ) {
        val rankMap: MutableMap<Rank, Int> = mutableMapOf()
        rankMap[rank] = expected
        val lottoResult = LottoResult(rankMap)
        val actual = lottoResult.getNum(rank)
        assertThat(actual).isEqualTo(expected)
    }
}
