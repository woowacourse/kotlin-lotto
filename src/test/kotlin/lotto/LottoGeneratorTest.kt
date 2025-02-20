package lotto

import lotto.domain.service.LottoGenerator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class LottoGeneratorTest {
    @Test
    fun `6개의 로또 번호를 생성한다`() {
        assertDoesNotThrow {
            val lottoGenerator = LottoGenerator()
            lottoGenerator.generateLotto()
        }
    }
}
