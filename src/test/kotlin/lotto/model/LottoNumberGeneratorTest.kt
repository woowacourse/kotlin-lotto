package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LottoNumberGeneratorTest {
    private lateinit var lottoNumbers: Set<LottoNumber>
    private lateinit var lotto: Lotto
    private val lottoNumberGenerator = LottoNumberGenerator()

    @BeforeEach
    fun setUp() {
        lottoNumbers = lottoNumberGenerator.generateAuto()
        lotto = Lotto(lottoNumbers)
    }

    @Test
    fun `로또 번호가 유효한 범위 내에 있는지 확인`() {
        val validRange = LottoNumberGenerator.START_RANGE..LottoNumberGenerator.END_RANGE
        assertThat(lottoNumbers).allMatch { it.number in validRange }
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
