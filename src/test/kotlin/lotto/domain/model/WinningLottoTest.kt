package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {

    @Test
    fun `보너스 번호가 로또 번호와 중복될 때 예외를 던진다`() {
        val exception = assertThrows<IllegalArgumentException> {
            WinningNumbers(
                Lotto(List(6) { LottoNumber(it + 1) }),
                LottoNumber(1)
            )
        }
        assertThat(exception.message).isEqualTo("보너스 번호는 당첨번호와 중복되면 안됩니다.")
    }

    @Nested
    @DisplayName("getRank 메서드 테스트")
    inner class GetRankTest {

        private val winningNumbers = Lotto(List(6) { LottoNumber(it + 1) })
        private val bonusNumber = LottoNumber(7)
        private val winningLotto = WinningNumbers(winningNumbers, bonusNumber)

        @Test
        fun `로또 번호가 6개 겹칠 때 1등을 반환한다`() {
            val targetLotto = Lotto(List(6) { LottoNumber(it + 1) })
            val actual = winningLotto.getRank(targetLotto)
            assertThat(actual).isEqualTo(Rank.FIRST)
        }

        @Test
        fun `로또 번호가 5개 겹치고 보너스가 일치할 때 2등을 반환한다`() {
            val targetLotto = Lotto(listOf(2, 3, 4, 5, 6, 7).map { LottoNumber(it) })
            val actual = winningLotto.getRank(targetLotto)
            assertThat(actual).isEqualTo(Rank.SECOND)
        }

        @Test
        fun `로또 번호가 5개 겹치고 보너스가 일치하지 않을 때 3등을 반환한다`() {
            val targetLotto = Lotto(listOf(2, 3, 4, 5, 6, 8).map { LottoNumber(it) })
            val actual = winningLotto.getRank(targetLotto)
            assertThat(actual).isEqualTo(Rank.THIRD)
        }

        @Test
        fun `로또 번호가 4개 겹칠 때 4등을 반환한다`() {
            val targetLotto = Lotto(listOf(3, 4, 5, 6, 7, 8).map { LottoNumber(it) })
            val actual = winningLotto.getRank(targetLotto)
            assertThat(actual).isEqualTo(Rank.FOURTH)
        }

        @Test
        fun `로또 번호가 3개 겹칠 때 5등을 반환한다`() {
            val targetLotto = Lotto(listOf(4, 5, 6, 7, 8, 9).map { LottoNumber(it) })
            val actual = winningLotto.getRank(targetLotto)
            assertThat(actual).isEqualTo(Rank.FIFTH)
        }

        @Test
        fun `로또 번호가 2개 겹칠 때 MISS를 반환한다`() {
            val targetLotto = Lotto(listOf(5, 6, 7, 8, 9, 10).map { LottoNumber(it) })
            val actual = winningLotto.getRank(targetLotto)
            assertThat(actual).isEqualTo(Rank.MISS)
        }

        @Test
        fun `로또 번호가 1개 겹칠 때 MISS를 반환한다`() {
            val targetLotto = Lotto(listOf(6, 7, 8, 9, 10, 11).map { LottoNumber(it) })
            val actual = winningLotto.getRank(targetLotto)
            assertThat(actual).isEqualTo(Rank.MISS)
        }

        @Test
        fun `로또 번호가 0개 겹칠 때 MISS를 반환한다`() {
            val targetLotto = Lotto(listOf(7, 8, 9, 10, 11, 12).map { LottoNumber(it) })
            val actual = winningLotto.getRank(targetLotto)
            assertThat(actual).isEqualTo(Rank.MISS)
        }
    }
}
