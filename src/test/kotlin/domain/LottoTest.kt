package domain

import common.convertToLottoNumberSet
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `개수가 6개면 로또 생성`() {
        val lotto = Lotto(setOf(1, 2, 3, 4, 5, 6).convertToLottoNumberSet())
        assertThat(lotto.numbers.size).isEqualTo(6)
    }

    @Test
    fun `개수가 6개가 아니라면 exception 발생`() {
        assertThrows<IllegalArgumentException> { Lotto(setOf(1, 2, 3, 4, 5).convertToLottoNumberSet()) }
    }

    @Test
    fun `번호가 범위안에 들어오지 않으면 exception 발생`() {
        assertThrows<IllegalArgumentException> { Lotto(setOf(1, 2, 3, 4, 5, 47).convertToLottoNumberSet()) }
    }

    @Test
    fun `자신의 번호와 정답번호를 비교해서 등수를 반환`() {
        val winningNumber = WinningLotto(Lotto(setOf(1, 2, 3, 4, 5, 7).convertToLottoNumberSet()), LottoNumber.from(6))
        val result = Lotto(setOf(1, 2, 3, 4, 5, 6).convertToLottoNumberSet()).matchResult(winningNumber)
        val expected = Rank.SECOND
        assertThat(result).isEqualTo(expected)
    }
}
