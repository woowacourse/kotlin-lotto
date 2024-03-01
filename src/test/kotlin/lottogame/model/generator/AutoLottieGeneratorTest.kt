package lottogame.model.generator

import lottogame.model.Lotto
import lottogame.model.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class AutoLottieGeneratorTest {
    private lateinit var numbersGenerator: NumbersGenerator

    @BeforeEach
    fun setUp() {
        numbersGenerator = NumbersGenerator { _, _ -> listOf(1, 2, 3, 4, 5, 6) }
    }

    @Test
    fun `Lotto 리스트를 생성한다`() {
        // given
        val lottieGenerator: AutoLottieGenerator = DefaultAutoLottieGenerator(numbersGenerator = numbersGenerator)
        val purchaseCost = Money(1000)
        val expectedLottie = listOf(Lotto(1, 2, 3, 4, 5, 6))
        // when
        val actualLottie = lottieGenerator.generate(purchaseCost)
        // then
        assertThat(actualLottie).isEqualTo(expectedLottie)
    }

    @Test
    fun `Lotto 리스트를 랜덤 넘버로 1000 번 테스트`() {
        val lottieGenerator: AutoLottieGenerator =
            DefaultAutoLottieGenerator(price = Money(1), numbersGenerator = RandomNumberGenerator())
        val purchaseCost = Money(1000)
        assertDoesNotThrow { lottieGenerator.generate(purchaseCost) }
    }
}
