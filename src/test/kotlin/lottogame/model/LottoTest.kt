package lottogame.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또 하나에 들어가는 숫자가 6개가 아닌 경우, 예외를 발생시킨다`() {
        // given
        val nums = intArrayOf(1, 2, 3, 4, 5)
        val nums2 = intArrayOf(1, 2, 3, 4, 5, 6, 7)
        // then
        assertAll(
            { assertThrows<IllegalArgumentException> { Lotto(*nums) } },
            { assertThrows<IllegalArgumentException> { Lotto(*nums2) } },
        )
    }

    @Test
    fun `로또 하나에 들어가는 숫자는 6개다`() {
        val nums = intArrayOf(1, 2, 3, 4, 5, 6)
        assertThat(nums).hasSize(6)
    }

    @Test
    fun `로또 하나에 들어가는 숫자가 중복된다면, 예외를 발생시킨다`() {
        val nums = intArrayOf(1, 1, 2, 3, 4, 5)
        assertThrows<IllegalArgumentException> { Lotto(*nums) }
    }

    @Test
    fun `로또 넘버 리스트는 오름차순이 아니면, 예외를 발생시킨다`() {
        assertThrows<IllegalArgumentException> { Lotto(2, 1, 3, 4, 5, 6) }
    }

    @Test
    fun `로또는 해당 로또 넘버를 가지고 있는지 확인할 수 있다`() {
        // given
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        val lottoNumber: LottoNumber = GeneralLottoNumber(1)
        val lottoNumber2: LottoNumber = GeneralLottoNumber(7)
        // when
        val actualResult = lottoNumber in lotto
        val actualResult2 = lottoNumber2 in lotto
        // then
        assertAll(
            { assertThat(actualResult).isTrue },
            { assertThat(actualResult2).isFalse },
        )
    }

    @Test
    fun `로또는 다른 로또와 일치하는 로또 넘버 개수를 반환할 수 있다`() {
        // given
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        val lotto2 = Lotto(2, 3, 4, 5, 6, 7)
        val expectedMatchedCount = 5
        // when
        val actualMatchedCount = lotto.getMatchCount(lotto2)
        // then
        assertThat(actualMatchedCount).isEqualTo(expectedMatchedCount)
    }
}
