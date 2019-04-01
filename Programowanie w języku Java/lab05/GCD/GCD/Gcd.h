#pragma once
#include "C:\Program Files\Java\jdk1.8.0_201\include\jni.h"
#include "C:\Program Files\Java\jdk1.8.0_201\include\win32\jni_md.h"
#include <stdio.h>

#ifndef _Included_GcdJNI
#define _Included_GcdJNI
#ifdef __cplusplus

extern "C" {
#endif
	/*
	 * Class:     lab05_GCD
	 * Method:    multi01
	 * Signature: ([Ljava/lang/Double;[Ljava/lang/Double;)Ljava/lang/Double;
	 */
	JNIEXPORT jobject JNICALL Java_GcdJNI_multi01
	(JNIEnv *, jobject, jobjectArray, jobjectArray);

	/*
	 * Class:     lab05_GCD
	 * Method:    multi02
	 * Signature: ([Ljava/lang/Double;)Ljava/lang/Double;
	 */
	JNIEXPORT jobject JNICALL Java_GcdJNI_multi02
	(JNIEnv *, jobject, jobjectArray);

	/*
	 * Class:     lab05_GCD
	 * Method:    multi03
	 * Signature: ()V
	 */
	JNIEXPORT void JNICALL Java_GcdJNI_multi03
	(JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif

class GCD
{
public:
	GCD();
	~GCD();
};