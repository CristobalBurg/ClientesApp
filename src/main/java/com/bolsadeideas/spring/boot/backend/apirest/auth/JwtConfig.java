package com.bolsadeideas.spring.boot.backend.apirest.auth;
//clase para guardar constantes de las firmas de seguridad del token
//esas RSA se crean con OPENSSL , una herramienta para criptografiar un token y hacerlo mas seguro
public class JwtConfig {
	public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEowIBAAKCAQEA1PvV9xUDQ/Ez0xoiHV1Uf33hwKoGwKEvXumU87/ng5js9drp\r\n" + 
			"gy8skfHCZzDsL2pSurynodrh+3cL0FVJW7+eA4EAIl8s3H58kPNZizhVLXmaPSkn\r\n" + 
			"cEflvrdjQz1JvR2gXD7/Pv7K2EfgXmqgN85+6vK5CS2lvrIaUlshni3e5x0pEs5p\r\n" + 
			"Zr9HdRws5SgZlN3ftTM8GFa9qX3uIZCjR/4+lTuJVdsI+9pq0RU/xY4SID+UK+aW\r\n" + 
			"JOlIaEc/2EQ6gEyN86Rfpmp6E2pqytYD2vwCl2a2SKZnYcYugqwEEE0AtIjIHIN6\r\n" + 
			"yosnEVMnwLwKpNSQA6sbB10OBP0Xu5BKwkNiDQIDAQABAoIBABxoHmifF9lnAVeF\r\n" + 
			"o8kw2oVKn+KXiuEEXpIVK3W7QcCuk+RSt1R05Uft69ThlI7aaoBXizAuRnfIr/2w\r\n" + 
			"cmFXVFb5TLBI623ZPPoESwwjjlDLaEQCCi2SmVTvlsCwGvarTekfyeF4EEv6gjV1\r\n" + 
			"mTr4QrtJdl6ABRxDGvmjB//V9GX0okFJymeq+8E4zmzotekSnJRB15H86shV4Gmi\r\n" + 
			"PPFOhzT5nTyMlJ+HOD4LNcFckdVYPEOQJJx3xbKSxMh0yqYjvnJCvpdJ7JoeoCBA\r\n" + 
			"tpshBqwrVzXZ3he2Gw4Y5JWwEJ/lU5IsHcY0yQfxPHLA6uR+HS3QLzbYJN8XQzrw\r\n" + 
			"KlrFcC0CgYEA+3XYVl2+jF8GHP+RjL1xMm87ViI9wp6jHkbaA6rNIxqe/6Qwzwg8\r\n" + 
			"k12OrvHhMCAicE4axb+nVnITaJU4qoLLQq6hpJTCUcw494DyEpTIcsaVSKyEI2Z+\r\n" + 
			"z7KuVkKHNsNajY6/by9Vdx+1ncE6jNyKyxy2wndUf2JgM8Eh8K7nvksCgYEA2NQq\r\n" + 
			"mGzcbnb7N0vp77E/d65z9tX0hlNDSXgsBO6YvWw8lipkdYTMU8Sb3Qt8whRv6tmY\r\n" + 
			"nWN1uF9ygVOTXUvHRf9rJOmEqhP2i+S8pKJSFb2a8VaiBKzA+lnn/ILOtlme0xi2\r\n" + 
			"uKBCkRVACuvBaBGJEYyQNkQyvTlwN9JiP2WSygcCgYEA0rlGudmFPQzEiriAuHho\r\n" + 
			"y+L6zouTRey6tTyapkgT8D72zzRA0r4MRVI5p9F67GGBeqt5NA4PpRmjuhiSjBFf\r\n" + 
			"VFoTi2A+F14UvxSHx5p2//f6UhSAB0p1KoiKIC9QMEw1LjtZoUsBS/p+r9ylvDV0\r\n" + 
			"+hDt9rkZTkaaZ8SJNLDd81ECgYB597bSMQI5/SHI9CHum7TI3NAMfEPlP3btH/Ua\r\n" + 
			"/R/cBcN68PO7i2zofgLy72BJR/fAkpAfkHAKu14M03dklpjMjiW2CMmAZmRcQtm+\r\n" + 
			"fjRQWGyM7x+KI6cV7HbreqVOu9D0i8VUy4lGVT7GJeGQabTBs2D3tZsZB/NQ4z4y\r\n" + 
			"zS8qBwKBgHrApyvKM2dM1ZZ11uRk6AIz3K/jzFz4zJb22r2iXkIE3HPWPviyANiO\r\n" + 
			"e88p6eE4+vHrJmSRhpXB1qT+tjAjvTYeTOxRoxI17A/xwi16OKBd9NRV8CkqLVvh\r\n" + 
			"TRYOwfWdo2WoCLlCeIkO+eHwmwHdWCgak92kVSc51ulkxEUhkCh3\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1PvV9xUDQ/Ez0xoiHV1U\r\n" + 
			"f33hwKoGwKEvXumU87/ng5js9drpgy8skfHCZzDsL2pSurynodrh+3cL0FVJW7+e\r\n" + 
			"A4EAIl8s3H58kPNZizhVLXmaPSkncEflvrdjQz1JvR2gXD7/Pv7K2EfgXmqgN85+\r\n" + 
			"6vK5CS2lvrIaUlshni3e5x0pEs5pZr9HdRws5SgZlN3ftTM8GFa9qX3uIZCjR/4+\r\n" + 
			"lTuJVdsI+9pq0RU/xY4SID+UK+aWJOlIaEc/2EQ6gEyN86Rfpmp6E2pqytYD2vwC\r\n" + 
			"l2a2SKZnYcYugqwEEE0AtIjIHIN6yosnEVMnwLwKpNSQA6sbB10OBP0Xu5BKwkNi\r\n" + 
			"DQIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";
}
