####Proyecto Universidad

Este es tu ID de cliente
259754224299-c1l9uhs70abf0fadaq64fgoja07i8gcc.apps.googleusercontent.com

Este es tu secreto de cliente
kVmG_G4Fvq0vSd3cFoZGC2ax

Departamento para los **recursos humanos**: capacitación, nómina

> Datos del empleado
    
    - cédula
    - mombre y apellido
    - direccion
    - status
    - cargo
        - gerente general
        - gerentes
        - administradores
        - contadores
        - almacenistas
        - vendedores (bonificación)
        - limpieza
        - secretaria
    - fecha de nacimiento
    - fecha de inicio
    - fecha de culminación
    - sueldo
    
> Variables globales

    - Salario base = SB : sueldo básico / 30
    - Precio unidad tributaria = PT
    - Salario integral diario = SID :  

> Cesta ticket

    : CT = (PT * 17) * 30
    
> Salario integral o base

    : SIB = SB + Utilidades + Vacaciones
    
> Utilidades
    
    : Utilidades = [(60 dias / 12 meses) / 30] * SB
    
> Vacaciones

    - La formula para calcular los días: 15 + (años de servicio - 1) máximo 30 días
    - La formula para calcular la remuneración: (salario diario de ese año) x (días hábiles)
    : Vacaciones = [(15 (d/a+ 1) / 12) / 30] * SB 
    
> Bono vacacional
    
    La formula para calcular los días: 7 + (años de servicio - 1)
    La formula para calcular la remuneración: (salario diario de ese año) x (días hábiles)
    : 
    
> Prestaciones Sociales

    : 

> Liquidación

    - (10) días de salario si la antigüedad fuere mayor de tres (3) meses y no excediere de seis (6) meses.
    - (30) días de salario por cada año de antigüedad o fracción superior de seis (6) meses, hasta un máximo de ciento cincuenta (150) días de salario.
    - Quince (15) días de salario, cuando la antigüedad fuere mayor de un (1) mes y no exceda de seis (6) meses;
    - Treinta (30) días de salario, cuando fuere superior a seis (6) meses y menor de un (1) año;
    - Cuarenta y cinco (45) días de salario, cuando fuere igual o superior a un (1) año;
    - Sesenta (60) días de salario, cuando fuere igual o superior a dos (2) años y no mayor de diez (10) años; y
    - Noventa (90) días de salario, si excediere del límite anterior.
    
    - Tipo de liquidacion (Despido o Justificado)
    - Tipo de nómina (semanal o quincenal/mensual)
    - Fecha de ingreso
    - Fecha de egreso
    - Salario mensual
    - Días de vacaciones del año
    - Días de bono vacaional
    - Días de utilidades del año
    
    : Fecha = inicio y fin de empleo
    : Salario mensual normal =  
    : Salario diario normal = SMN / 30
    : Alicuota de actividades = SDN * dias de utilidades
    : Alicuota de bono vacacional = (SDN * 15 (+1 por año hasta 30) / 360
    : Salario integral = SDN + AA + ABV
    : Prestaciones sociales = (cada 3 meses : el salario integral * 15)
    : Prestaciones sociales acumuladas = (cada 3 meses : PS + PSA anterior)
    
> Vales

    - Vales por liquidación
        : 
    - Vales por sueldo
        : Vales = (Días restante al pago - Días laborados) * SD
    
> Antguedad

    - Por cada año se le paga 2 días de salario hasta 30 días
    - Despido por: 
        : (3 a 6 meses) pago de 15 días de salario
        : (6 a 12 meses) pago de 45 días
        : (1 año o mas) pago de 60 días 
    - Prestación por antigüedad 
        : (Salario integral del mes / días laborados del mes) x 5 
        - (o simplemente sume 5 días de salario integral del mes)
    
#####Reportes

> Generar factura de salario intergral

    - Asignaciones
        : Días hábiles = 11 * SB
        : Días descanso = 4 * SB
        : Bono Nocturno 
        : Bono de lealtad
        : Bonificación solo vendedores = % por ventas
        : Días feriados = (Días Feriados  * SB) * 2
        : TotalA = sumatoria
        
    - Deducciones
        : FAOV  = 1% * TotalA
        : IVSS = 4% * TotalA
        : Paro forzoso = 0.5% * TotalA
        : Prestamo
        : Dias no laborados = Días no laborados * SB
        : TotalD = resta
     
     - Total de la quincena = TotalA - TotalD
    
> Generar reporte por cada calculo (considerar fechas)

    - Cesta Ticket
    - Sueldo Integral
    - Utilidades
    - Bono Vacacional
    - Vaciones
    - Vales o Prestamos
    - Prestaciones Sociales
    - Liquidaciones

#####Descuentos
    
#####Consideraciones
    Periodo de Trabajo:
        1 mes - 7 días (una semana)
        6 meses - 15 días (una quincena)
        1 año - 30 días (un mes).

    Sólo se paga por 3 días justificados luego lo paga el estado
        
    