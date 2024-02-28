package domain

import lotto.model.Lotto
import lotto.model.MakeLottoStrategy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {

    private lateinit var makeLottoStrategy: MakeLottoStrategy

    @BeforeEach
    fun setUp() {
        makeLottoStrategy = MakeLottoStrategy.MakeSortedRandomLotto(1..45)
    }

    @Test
    fun `로또 번호가 5개 일 때 예외를 던지는 지`() {
        val exception = assertThrows<IllegalArgumentException> { Lotto(1, 2, 3, 4, 5) }

        assertThat(exception.message).isEqualTo("입력된 로또 숫자는 5개입니다. 로또 숫자는 고유한 6개여야 합니다.")
    }

    @Test
    fun `로또 번호가 서로 중복될 때 예외를 던진다`() {
        val exception = assertThrows<IllegalArgumentException> { Lotto(1, 2, 3, 4, 5, 5) }

        assertThat(exception.message).isEqualTo("입력된 로또 숫자는 5개입니다. 로또 숫자는 고유한 6개여야 합니다.")
    }

    @Test
    fun `로또 숫자 개수가 6개인지 확인한다`() {
        val lottoSize = 6

        val expected = Lotto.makeLotto(makeLottoStrategy).numbers.size

        assertThat(expected).isEqualTo(lottoSize)
    }

    @Test
    fun `결과 로또가 오름차순인지 확인한다`() {
        val expected = Lotto.makeLotto(makeLottoStrategy).numbers
        val actual = expected.sortedBy { it.value }.toSet()

        assertThat(expected).isEqualTo(actual)
    }
}
