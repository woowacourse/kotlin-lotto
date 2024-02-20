import model.AutoLottoGenerator
import model.Lotto
import model.LottoGenerator
import model.Money
import model.NumbersGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RandomLottoGeneratorTest {
    private lateinit var numbersGenerator: NumbersGenerator

    @BeforeEach
    fun setUp() {
        numbersGenerator = NumbersGenerator { listOf(1, 2, 3, 4, 5, 6) }
    }

    @Test
    fun `Lotto 리스트를 생성한다`() {
        // given
        val lottoGenerator: LottoGenerator = AutoLottoGenerator(numbersGenerator = numbersGenerator)
        val purchaseCost = Money(1000)
        val expectedLottie = listOf(Lotto(1, 2, 3, 4, 5, 6))
        // when
        val actualLottie = lottoGenerator.generate(purchaseCost)
        // then
        assertThat(actualLottie).isEqualTo(expectedLottie)
    }
}
