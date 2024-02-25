package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoTest {

    @Test
    fun `로또 번호는 6개의 숫자를 가진다`() {
        val lotto = Lotto(List(6) { LottoNumber(it + 1) })
        assertThat(lotto.numbers.size).isEqualTo(6)
    }

    @ParameterizedTest
    @ValueSource(ints = [5, 7])
    fun `로또 번호가 6개가 아닐 때 예외를 던진다`(size: Int) {
        val lottoNumbers = List(size) { LottoNumber(it + 1) }
        val exception = assertThrows<IllegalArgumentException> { Lotto(lottoNumbers) }
        assertThat(exception.message).isEqualTo("로또 번호는 6개여야 합니다.")
    }

    @Test
    fun `로또 번호가 서로 중복될 때 예외를 던진다`() {
        val lottoNumbers = List(6) { LottoNumber(1) }
        val exception = assertThrows<IllegalArgumentException> { Lotto(lottoNumbers) }
        assertThat(exception.message).isEqualTo("로또 번호끼리는 중복되면 안됩니다.")
    }

    @Nested
    @DisplayName("getMatchCountTest 메서드 테스트")
    inner class GetMatchCountTest {
        @Test
        fun `로또 번호가 6개 겹칠 때 적절한 수를 반환한다`() {
            val targetLotto = Lotto(List(6) { LottoNumber(it + 1) })
            val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) })
            val actual = targetLotto.getMatchCount(winningLotto)
            assertThat(actual).isEqualTo(6)
        }

        @Test
        fun `로또 번호가 5개 겹칠 때 적절한 수를 반환한다`() {
            val targetLotto = Lotto(List(6) { LottoNumber(it + 1) })
            val winningLotto = Lotto(listOf(2, 3, 4, 5, 6, 7).map { LottoNumber(it) })
            val actual = targetLotto.getMatchCount(winningLotto)
            assertThat(actual).isEqualTo(5)
        }

        @Test
        fun `로또 번호가 4개 겹칠 때 적절한 수를 반환한다`() {
            val targetLotto = Lotto(List(6) { LottoNumber(it + 1) })
            val winningLotto = Lotto(listOf(3, 4, 5, 6, 7, 8).map { LottoNumber(it) })
            val actual = targetLotto.getMatchCount(winningLotto)
            assertThat(actual).isEqualTo(4)
        }

        @Test
        fun `로또 번호가 3개 겹칠 때 적절한 수를 반환한다`() {
            val targetLotto = Lotto(List(6) { LottoNumber(it + 1) })
            val winningLotto = Lotto(listOf(4, 5, 6, 7, 8, 9).map { LottoNumber(it) })
            val actual = targetLotto.getMatchCount(winningLotto)
            assertThat(actual).isEqualTo(3)
        }

        @Test
        fun `로또 번호가 2개 겹칠 때 적절한 수를 반환한다`() {
            val targetLotto = Lotto(List(6) { LottoNumber(it + 1) })
            val winningLotto = Lotto(listOf(5, 6, 7, 8, 9, 10).map { LottoNumber(it) })
            val actual = targetLotto.getMatchCount(winningLotto)
            assertThat(actual).isEqualTo(2)
        }

        @Test
        fun `로또 번호가 1개 겹칠 때 적절한 수를 반환한다`() {
            val targetLotto = Lotto(List(6) { LottoNumber(it + 1) })
            val winningLotto = Lotto(listOf(6, 7, 8, 9, 10, 11).map { LottoNumber(it) })
            val actual = targetLotto.getMatchCount(winningLotto)
            assertThat(actual).isEqualTo(1)
        }

        @Test
        fun `로또 번호가 0개 겹칠 때 적절한 수를 반환한다`() {
            val targetLotto = Lotto(List(6) { LottoNumber(it + 1) })
            val winningLotto = Lotto(listOf(7, 8, 9, 10, 11, 12).map { LottoNumber(it) })
            val actual = targetLotto.getMatchCount(winningLotto)
            assertThat(actual).isEqualTo(0)
        }
    }

    @Nested
    @DisplayName("isContain 메서드 테스트")
    inner class IsContainTest {
        @Test
        fun `로또 번호에 보너스 번호가 포함되면 true를 반환한다`() {
            val targetLotto = Lotto(List(6) { LottoNumber(it + 1) })
            val bonusNumber = LottoNumber(1)
            val actual = targetLotto.isContain(bonusNumber)
            assertTrue(actual)
        }

        @Test
        fun `로또 번호에 보너스 번호가 포함되지 않으면 false를 반환한다`() {
            val targetLotto = Lotto(List(6) { LottoNumber(it + 1) })
            val bonusNumber = LottoNumber(7)
            val actual = targetLotto.isContain(bonusNumber)
            assertFalse(actual)
        }
    }
}
