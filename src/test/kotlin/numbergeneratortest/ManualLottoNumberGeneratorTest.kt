import domain.ManualLottoNumbersGenerator
import domain.model.lotto.LottoNumber
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ManualLottoNumberGeneratorTest {

    lateinit var manualLottoNumbersGenerator: ManualLottoNumbersGenerator

    @BeforeEach
    fun setUp() {
        manualLottoNumbersGenerator = ManualLottoNumbersGenerator()
    }

    @Test
    fun `수동으로 로또 번호를 생성하기`() {
        val lottoNumbers = manualLottoNumbersGenerator.generate(listOf(1, 2, 3, 4, 5))

        lottoNumbers.forEach { lottoNumber ->
            assertThat(lottoNumber).isInstanceOf(LottoNumber::class.java)
        }
    }
}