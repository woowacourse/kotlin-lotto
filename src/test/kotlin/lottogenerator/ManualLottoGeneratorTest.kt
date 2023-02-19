package lottogenerator

import domain.lottogenerator.ManualLottoGenerator
import domain.model.lotto.Lotto
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ManualLottoGeneratorTest {

    lateinit var manualLottoGenerator: ManualLottoGenerator

    @BeforeEach
    fun setUp() {
        manualLottoGenerator = ManualLottoGenerator()
    }

    @Test
    fun `수동으로 로또를 생성하기`() {
        val lotto = manualLottoGenerator.generate(listOf(1, 2, 3, 4, 5))

        assertThat(lotto).isInstanceOf(Lotto::class.java)
    }
}