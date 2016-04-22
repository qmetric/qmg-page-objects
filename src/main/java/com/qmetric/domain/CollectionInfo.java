package com.qmetric.domain;

public class CollectionInfo
{
    public final int collectionIndex;
    public final String previousCollectionId;

    public CollectionInfo(int collectionIndex, String previousCollectionId) {
        this.collectionIndex = collectionIndex;
        this.previousCollectionId = previousCollectionId;
    }
}
