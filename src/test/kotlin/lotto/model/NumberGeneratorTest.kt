package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class NumberGeneratorTest {
    private lateinit var lottoNumbers: List<LottoNumber>
    private lateinit var lotto: Lotto
    private val numberGenerator = NumberGenerator()

    @BeforeEach
    fun setUp() {
        lottoNumbers = numberGenerator.generateLottoNumbers().map { LottoNumber(it) }
        lotto = Lotto(lottoNumbers.toSet())
    }

    @Test
    fun `로또 번호가 시작 범위에서 마지막 범위사이의 숫자인지 확인`() {
        assertThat(lottoNumbers).allMatch { it.number in NumberGenerator.START_RANGE..NumberGenerator.END_RANGE }
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

        assertThat(actual).isEqualTo(6)
    }

    @Test
    fun `로또 번호가 오름차순인지 확인`() {
        assertThat(lottoNumbers.map { it.number }).isSorted()
    }
}
