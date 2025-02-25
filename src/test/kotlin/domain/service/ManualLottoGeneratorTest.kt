package domain.service

import domain.model.Lotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ManualLottoGeneratorTest {
    @Test
    fun `수동으로 로또가 생성된다`() {
        val manualLottoNumbers = setOf(1, 2, 3, 4, 5, 6)
        val lottoGenerator = ManualLottoGenerator()

        lottoGenerator.setManualLottoNumbers(manualLottoNumbers)
        val lotto: Lotto = lottoGenerator.makeLotto()

        assertThat(lotto).isEqualTo(Lotto.of(1, 2, 3, 4, 5, 6))
    }
}
