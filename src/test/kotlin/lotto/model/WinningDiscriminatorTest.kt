package lotto.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningDiscriminatorTest {
    @Test
    fun `6개의 번호가 일치하면 1등을 반환한다`() {
        // given
        val lottoNumbers = listOf(1, 2, 3, 4, 5, 6)
        val winningLotto = Lotto.from(lottoNumbers)
        val bonusNumber = LottoNumber.from(7)
        val winningDiscriminator = WinningDiscriminator(winningLotto, bonusNumber)
        val lotto = listOf(Lotto.from(lottoNumbers))

        // when
        val discriminateResult = winningDiscriminator.getResult(lotto)

        // then
        assertEquals(1, discriminateResult[Rank.FIRST])
    }

    @Test
    fun `5개의 번호가 일치하면 3등을 반환한다`() {
        // given
        val lottoNumbers = listOf(1, 2, 3, 4, 5, 8)
        val winningLotto = Lotto.from(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = LottoNumber.from(7)
        val winningDiscriminator = WinningDiscriminator(winningLotto, bonusNumber)
        val lotto = listOf(Lotto.from(lottoNumbers))

        // when
        val discriminateResult = winningDiscriminator.getResult(lotto)

        // then
        assertEquals(1, discriminateResult[Rank.THIRD])
    }

    @Test
    fun `4개의 번호가 일치하면 4등을 반환한다`() {
        // given
        val lottoNumbers = listOf(1, 2, 3, 4, 9, 10)
        val winningLotto = Lotto.from(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = LottoNumber.from(7)
        val winningDiscriminator = WinningDiscriminator(winningLotto, bonusNumber)
        val lotto = listOf(Lotto.from(lottoNumbers))

        // when
        val discriminateResult = winningDiscriminator.getResult(lotto)

        // then
        assertEquals(1, discriminateResult[Rank.FOURTH])
    }

    @Test
    fun `3개의 번호가 일치하면 5등을 반환한다`() {
        // given
        val lottoNumbers = listOf(1, 2, 3, 8, 9, 10)
        val winningLotto = Lotto.from(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = LottoNumber.from(7)
        val winningDiscriminator = WinningDiscriminator(winningLotto, bonusNumber)
        val lotto = listOf(Lotto.from(lottoNumbers))

        // when
        val discriminateResult = winningDiscriminator.getResult(lotto)

        // then
        assertEquals(1, discriminateResult[Rank.FIFTH])
    }

    @Test
    fun `2개 이하의 번호가 일치하면 MISS를 반환한다`() {
        listOf(
            listOf(1, 2, 23, 24, 25, 26),
            listOf(1, 22, 23, 24, 25, 26),
            listOf(21, 22, 23, 24, 25, 26),
        ).forEach { lottoNumbers ->
            // given
            val winningLotto = Lotto.from(listOf(1, 2, 3, 4, 5, 6))
            val bonusNumber = LottoNumber.from(7)
            val winningDiscriminator = WinningDiscriminator(winningLotto, bonusNumber)
            val lotto = listOf(Lotto.from(lottoNumbers))

            // when
            val discriminateResult = winningDiscriminator.getResult(lotto)

            // then
            assertEquals(1, discriminateResult[Rank.MISS])
        }
    }

    @Test
    fun `보너스 번호가 일치하면서 5개의 번호가 동일한 경우 2등을 반환한다`() {
        // given
        val lottoNumbers = listOf(1, 2, 3, 4, 5, 7)
        val winningLotto = Lotto.from(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = LottoNumber.from(7)
        val winningDiscriminator = WinningDiscriminator(winningLotto, bonusNumber)
        val lotto = listOf(Lotto.from(lottoNumbers))

        // when
        val discriminateResult = winningDiscriminator.getResult(lotto)

        // then
        assertEquals(1, discriminateResult[Rank.SECOND])
    }

    @Test
    fun `당첨 번호와 보너스 번호가 중복되면 오류를 반환한다`() {
        // given
        val winningLotto = Lotto.from(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = LottoNumber.from(1)

        // when & then
        assertThrows<IllegalArgumentException> {
            WinningDiscriminator(winningLotto, bonusNumber)
        }
    }

    @Test
    fun `당첨 번호끼리 중복되지 않았을 때, 당첨 번호와 보너스 번호와 중복되면 오류를 반환한다`() {
        // given
        val winningLotto = Lotto.from(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = LottoNumber.from(1)

        // when & then
        assertThrows<IllegalArgumentException> {
            WinningDiscriminator(winningLotto, bonusNumber)
        }
    }
}
