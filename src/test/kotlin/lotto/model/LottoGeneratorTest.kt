package lotto.model

import lotto.util.Constant
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LottoGeneratorTest {
    private lateinit var lotto: Lotto
    private lateinit var lottoNumbers: List<LottoNumber>

    @BeforeEach
    fun setUp() {
        lotto = LottoGenerator.generateLotto()
        lottoNumbers = lotto.getNumbers()
    }

    @Test
    fun `로또 번호가 1에서 45사이의 숫자인지 확인`() {
        assertThat(
            lottoNumbers.all { number ->
                number.getNumber() in Constant.MIN_LOTTO_NUMBER_RANGE..Constant.MAX_LOTTO_NUMBER_RANGE
            },
        ).isTrue
    }

    @Test
    fun `로또 번호가 중복되지 않는지 확인`() {
        val actual = lottoNumbers.size
        val expected = lottoNumbers.toSet().size

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `로또 번호가 6개인지 확인`() {
        val actual = lottoNumbers.size

        assertThat(actual).isEqualTo(Constant.LOTTO_SIZE)
    }

    @Test
    fun `로또 번호가 오름차순인지 확인`() {
        val lottoNumbers = lottoNumbers.map { it.getNumber() }
        assertThat(lottoNumbers).isEqualTo(lottoNumbers.sorted())
    }
}
