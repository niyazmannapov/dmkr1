text = '''Во поле береза стояла,
Во поле кудрявая стояла,
Люли-люли, стояла,
Люли-люли, стояла.
Некому березу заломати,
Некому кудряву заломати,
Люли-люли, заломати,
Люли-люли, заломати.
Я ж пойду погуляю,
Белую березу заломаю,
Люли-люли, заломаю,
Люли-люли, заломаю.
Срежу я с березы три пруточка,
Сделаю из них я три гудочка,
Люли-люли, три гудочка,
Люли-люли, три гудочка.
Четвертую балалайку,
Четвертую балалайку,
Люли-люли, балалайку,
Люли-люли, балалайку.
Пойду на новые на сени,
Стану в балалаечку играти,
Люли-люли, играти,
Люли-люли, играти!'''
tokens = tokenizer.tokenize(text.lower())




m = len(set(text))
k = 2
n =int((k * m) // log(2) )



bloom_filter = [0] * n


for word in tokens:
    crc = zlib.crc32(bytes(word.encode())) % n
    adl = zlib.adler32(bytes(word.encode())) % n
    bloom_filter[crc] += 1
    bloom_filter[adl] += 1







fp = 0.5 ** ((len(bloom_filter)/len(set(text)))*log(2))