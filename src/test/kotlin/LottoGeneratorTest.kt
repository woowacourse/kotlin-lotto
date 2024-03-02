import lotto.model.LottoGenerator
import lotto.model.LottoNumber
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LottoGeneratorTest {
    @Test
    fun `generateLotto returns expected Lotto for test environment`() {
        val testNumberGenerator = TestLottoNumberGenerator()
        val lottoGenerator = LottoGenerator(testNumberGenerator)
        val expectedNumbers = setOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) }.toSet()

        val lotto = lottoGenerator.generateLotto()

        assertEquals(expectedNumbers, lotto.numbers, "Generated Lotto numbers should match the expected numbers")
    }
}
