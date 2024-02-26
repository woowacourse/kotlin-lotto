import lotto.model.LottoGenerator
import lotto.util.Constant
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class LottoGeneratorTest {
    @Test
    fun `generateLotto returns a Lotto with valid numbers`() {
        val lottoGenerator = LottoGenerator()
        val lotto = lottoGenerator.generateLotto()

        // 번호 개수 검사
        assertTrue(lotto.numbers.size == Constant.LOTTO_LEN, "Lotto should contain ${Constant.LOTTO_LEN} numbers")

        // 번호 범위 검사
        lotto.numbers.forEach {
            assertTrue(it.lottoNumber in Constant.LOTTO_NUM_RANGE, "Lotto number should be within the valid range")
        }

        // 고유성 검사
        assertTrue(lotto.numbers.size == lotto.numbers.distinctBy { it.lottoNumber }.size, "Lotto numbers should be unique")
    }
}
