package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoResultTest {
    @Test
    fun `6개 일치 시 1등`() {
        val wonLotto = WinLotto(Lotto((1..6).map { LottoNumber(it) }.toSet()), bonusNumber = LottoNumber(7))
        val expectedFirstLotto = Lotto(setOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }.toSet())
        assertThat(LottoResult.from(wonLotto, expectedFirstLotto)).isEqualTo(LottoResult.FIRST)
    }

    @Test
    fun `5개 일치, 보너스 볼 일치 시 2등`() {
        val wonLotto = WinLotto(Lotto((1..6).map { LottoNumber(it) }.toSet()), bonusNumber = LottoNumber(7))
        val expectedSecondLotto = Lotto(setOf(1, 2, 3, 4, 5, 7).map { LottoNumber(it) }.toSet())
        assertThat(LottoResult.from(wonLotto, expectedSecondLotto)).isEqualTo(LottoResult.SECOND)
    }

    @Test
    fun `5개 일치 시 3등`() {
        val wonLotto = WinLotto(Lotto((1..6).map { LottoNumber(it) }.toSet()), LottoNumber(7))
        val expectedThirdLotto = Lotto(setOf(1, 2, 3, 4, 5, 45).map { LottoNumber(it) }.toSet())
        assertThat(LottoResult.from(wonLotto, expectedThirdLotto)).isEqualTo(LottoResult.THIRD)
    }

    @Test
    fun `4개 일치 시 4등`() {
        val wonLotto = WinLotto(Lotto((1..6).map { LottoNumber(it) }.toSet()), LottoNumber(7))
        val expectedFourthLotto = Lotto(setOf(1, 2, 3, 4, 44, 45).map { LottoNumber(it) }.toSet())
        assertThat(LottoResult.from(wonLotto, expectedFourthLotto)).isEqualTo(LottoResult.FOURTH)
    }

    @Test
    fun `3개 일치 시 5등`() {
        val wonLotto = WinLotto(Lotto((1..6).map { LottoNumber(it) }.toSet()), LottoNumber(7))
        val expectedFifthLotto = Lotto(setOf(1, 2, 3, 43, 44, 45).map { LottoNumber(it) }.toSet())
        assertThat(LottoResult.from(wonLotto, expectedFifthLotto)).isEqualTo(LottoResult.FIFTH)
    }

    @Test
    fun `2개 이하 일치 시 낙첨`() {
        val wonLotto = WinLotto(Lotto((1..6).map { LottoNumber(it) }.toSet()), LottoNumber(7))
        val expectedFailLotto = Lotto(setOf(1, 2, 42, 43, 44, 45).map { LottoNumber(it) }.toSet())
        assertThat(LottoResult.from(wonLotto, expectedFailLotto)).isEqualTo(LottoResult.FAIL)
    }
}
