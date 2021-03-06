/*
 * Copyright 2011 Foundation for On-Line Genealogy, Inc.
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

package org.folg.names.score;

/**
 * Sum weighted features to compute a score
 * The weights were generated by running training data provided by Ancestry.com through Weka's Logistic learner
 */
public class FeaturesScorer {
   private final boolean isSurname;

   public FeaturesScorer(boolean isSurname) {
      this.isSurname = isSurname;
   }

   public double score(Features features) {
      if (isSurname) {
// Logistic (F=86.2)
//wed          -5.6122
//nys           0.4026
//sdx           0.1687
//rsdx          0.5052
//dmsdx         0.9679
//lev           0.3696
//Intercept     7.7697
      return 7.7697 +
              features.weightedEditDistance * -5.6122 +
              features.nysiis * 0.4026 +
              features.soundex * 0.1687 +
              features.refinedSoundex * 0.5052 +
              features.dmSoundex * 0.9679 +
              features.levenstein * 0.3696;
      }
      else {
// Logistic (F=94.4)
//wed          -7.3705
//sdx           0.0699
//rsdx          0.6476
//dmsdx         1.2748
//lev           0.4359
//Intercept    11.5572
         return 11.5572 +
                 features.weightedEditDistance * -7.3705 +
                 // nysiis not useful for givennames
                 features.soundex * 0.0699 +
                 features.refinedSoundex * 0.6476 +
                 features.dmSoundex * 1.2748 +
                 features.levenstein * 0.4359;
      }
   }
}
