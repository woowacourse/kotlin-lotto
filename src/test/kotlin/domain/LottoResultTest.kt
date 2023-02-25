package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class LottoResultTest {
    @Test
    fun `로또 결과 중 0이 아니라 널 값이 있다면 에러 발생`() {
        val result = Rank.values().filterNot { it == Rank.SECOND }.associateWith { 0 }.toMutableMap()
        assertThrows<IllegalArgumentException> { LottoResult(result) }
    }

    @Test
    fun `로또 결과 중 모든 등수에 대해 0이상의 집계 결과가 있다면 에러 발생하지 않음`() {
        val result = Rank.values().associateWith { 0 }.toMutableMap()
        assertDoesNotThrow { LottoResult(result) }
    }

    @Test
    fun `당첨금액의 합계가 올바르게 나오는지 검증`() {
        val rankInfo = Rank.values().associateWith { 0 }.toMutableMap()
        rankInfo[Rank.FIRST] = 0
        rankInfo[Rank.SECOND] = 1
        rankInfo[Rank.THIRD] = 2
        rankInfo[Rank.FOURTH] = 0
        rankInfo[Rank.FIFTH] = 1
        val lottoResult = LottoResult(rankInfo)
        val result = lottoResult.sumWinningMoney()
        var expected: Double = 0.0
        expected += Rank.SECOND.winningMoney * 1
        expected += Rank.THIRD.winningMoney * 2
        expected += Rank.FIFTH.winningMoney * 1
        assertThat(result).isEqualTo(expected)
    }
}
