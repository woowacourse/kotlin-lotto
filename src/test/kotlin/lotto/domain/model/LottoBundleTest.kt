package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoBundleTest {
    @Test
    fun `로또 번들에 로또가 0개 이하라면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            LottoBundle(listOf())
        }
    }

    @Test
    fun `로또 번들은 로또를 1개 이상 가지고 있어야 한다`() {
        assertDoesNotThrow {
            LottoBundle(List(1) { Lotto(listOf(1, 2, 3, 4, 5, 6)) })
        }
    }

    @CsvSource(
        "1, 2, 3",
        "2, 4, 6",
        "3, 11, 14",
    )
    @ParameterizedTest(name = "크기가 {0}인 로또 번들과 크기가 {1}인 로또 번들을 결합하면 크기가 {2}인 로또 번들이 되야한다 ")
    fun `로또 번들을 결합할 수 있다`(
        size1: Int,
        size2: Int,
        expected: Int,
    ) {
        val lottoBundle1 = LottoBundle(List(size1) { Lotto(listOf(1, 2, 3, 4, 5, 6)) })
        val lottoBundle2 = LottoBundle(List(size2) { Lotto(listOf(1, 2, 3, 4, 5, 6)) })

        val actual = LottoBundle.combine(lottoBundle1, lottoBundle2).lottos.size
        assertThat(actual).isEqualTo(expected)
    }
}
