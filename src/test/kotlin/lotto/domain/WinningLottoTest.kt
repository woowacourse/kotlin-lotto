package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class WinningLottoTest {
    @Test
    fun `메인 번호는 6개이다`() {
        assertThrows<IllegalArgumentException> {
            WinningLotto(
                setOf<LottoNumber>(
                    LottoNumber.from(3),
                    LottoNumber.from(45),
                    LottoNumber.from(34),
                ),
                LottoNumber.from(36),
            )
        }
    }

    @Test
    fun `보너스 번호를 가진다`() {
        assertThat(
            WinningLotto(
                setOf<LottoNumber>(
                    LottoNumber.from(3),
                    LottoNumber.from(45),
                    LottoNumber.from(34),
                    LottoNumber.from(1),
                    LottoNumber.from(2),
                    LottoNumber.from(4),
                ),
                LottoNumber.from(10),
            )
                .bonusLottoNumber.value,
        ).isEqualTo(10)
    }

    @MethodSource("provideDuplicateNumbers")
    @ParameterizedTest
    fun `메인과 보너스 번호를 포함한 모든 번호는 서로 중복되지 않는다`(mainLottoNumbers: Set<LottoNumber>, bonusLottoNumber: LottoNumber) {
        assertThrows<IllegalArgumentException> {
            WinningLotto(mainLottoNumbers, bonusLottoNumber)
        }
    }

    companion object {
        @JvmStatic
        fun provideDuplicateNumbers() = listOf(
            Arguments.of(
                setOf<LottoNumber>(
                    LottoNumber.from(1),
                    LottoNumber.from(2),
                    LottoNumber.from(3),
                    LottoNumber.from(4),
                    LottoNumber.from(5),
                    LottoNumber.from(5),
                ),
                LottoNumber.from(6),
            ),
            Arguments.of(
                setOf<LottoNumber>(
                    LottoNumber.from(1),
                    LottoNumber.from(2),
                    LottoNumber.from(3),
                    LottoNumber.from(4),
                    LottoNumber.from(5),
                    LottoNumber.from(6),
                ),
                LottoNumber.from(6),
            ),
        )
    }
}
