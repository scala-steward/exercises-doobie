/*
 * Copyright 2016-2020 47 Degrees Open Source <https://www.47deg.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package doobielib

import org.scalacheck.ScalacheckShapeless._
import org.scalaexercises.Test
import org.scalatest.refspec.RefSpec
import org.scalatestplus.scalacheck.Checkers
import shapeless._

class ErrorHandlingSectionSpec extends RefSpec with Checkers {

  def `safe insert with attemptSome` = {
    check(
      Test.testSuccess(
        ErrorHandlingSection.safeInsertWithAttemptSome _,
        (Left("Oops!"): Either[String, Long]) :: HNil
      )
    )
  }

  def `safe insert with attemptSomeSqlState` = {
    check(
      Test.testSuccess(
        ErrorHandlingSection.safeInsertWithAttemptSomeSqlState _,
        (Left("John is already here!"): Either[String, Long]) :: HNil
      )
    )
  }

  def `safe insert with exceptSqlState` = {
    check(
      Test.testSuccess(
        ErrorHandlingSection.safeInsertWithExceptSqlState _,
        "John_20" :: Option(20) :: HNil
      )
    )
  }
}
