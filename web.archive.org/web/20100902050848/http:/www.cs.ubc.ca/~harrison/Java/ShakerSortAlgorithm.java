/*
 * @(#)ShakerSortAlgorithm.java	1.0 95/06/26 Jason Harrison
 *
 * Copyright (c) 1995 University of British Columbia
 *
 * Permission to use, copy, modify, and distribute this software
 * and its documentation for NON-COMMERCIAL purposes and without
 * fee is hereby granted provided that this copyright notice
 * appears in all copies. Please refer to the file "copyright.html"
 * for further important copyright and licensing information.
 *
 * UBC MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. UBC SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

/**
 * A shaker sort demonstration algorithm
 * SortAlgorithm.java, Thu Oct 27 10:32:35 1994
 *
 * @author Jason Harrison@cs.ubc.ca
 * @version 	1.0, 26 Jun 1995
 *
 */
class ShakerSortAlgorithm extends SortAlgorithm {
    void sort(int a[]) throws Exception {
	int i = 0;
	int k = a.length - 1;
	while (i < k) {
	    int min = i;
	    int max = i;
            int j;
            for (j = i + 1; j <= k; j++) {
	        if (stopRequested) {
		    return;
                }

		if (a[j] < a[min]) {
                    min = j;
                }
		if (a[j] > a[max]) {
                    max = j;
                }
	        pause(i,j);
	    }
            int T = a[min];
            a[min] = a[i];
	    a[i] = T;
	    pause(i,k);

	    if (max == i) {
	        T = a[min];
	        a[min] = a[k];
	 	a[k] = T;
	    } else {
	        T = a[max];
	        a[max] = a[k];
	        a[k] = T;
	    }
	    pause(i,k);
	    i++;
	    k--;
        }
    }
}
